Pod::Spec.new do |spec|
    spec.name                     = 'multiplatform_connectivity_status'
    spec.version                  = '1.2.0'
    spec.homepage                 = 'https://github.com/ln-12/multiplatform-connectivity-status'
    spec.source                   = { :http=> ''}
    spec.authors                  = ''
    spec.license                  = ''
    spec.summary                  = 'A Kotlin multiplatform mobile library to monitor the connectivity status of the device'
    spec.vendored_frameworks      = 'build/cocoapods/framework/multiplatform_connectivity_status.framework'
    spec.libraries                = 'c++'
                
    spec.dependency 'Reachability', '3.2'
                
    spec.pod_target_xcconfig = {
        'KOTLIN_PROJECT_PATH' => ':',
        'PRODUCT_MODULE_NAME' => 'multiplatform_connectivity_status',
    }
                
    spec.script_phases = [
        {
            :name => 'Build multiplatform_connectivity_status',
            :execution_position => :before_compile,
            :shell_path => '/bin/sh',
            :script => <<-SCRIPT
                if [ "YES" = "$COCOAPODS_SKIP_KOTLIN_BUILD" ]; then
                  echo "Skipping Gradle build task invocation due to COCOAPODS_SKIP_KOTLIN_BUILD environment variable set to \"YES\""
                  exit 0
                fi
                set -ev
                REPO_ROOT="$PODS_TARGET_SRCROOT"
                "$REPO_ROOT/gradlew" -p "$REPO_ROOT" $KOTLIN_PROJECT_PATH:syncFramework \
                    -Pkotlin.native.cocoapods.platform=$PLATFORM_NAME \
                    -Pkotlin.native.cocoapods.archs="$ARCHS" \
                    -Pkotlin.native.cocoapods.configuration="$CONFIGURATION"
            SCRIPT
        }
    ]
                
end